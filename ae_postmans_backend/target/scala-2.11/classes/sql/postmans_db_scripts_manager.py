import MySQLdb
import warnings
import ConfigParser
import StringIO
import sys, getopt
import datetime, time ,os , re
SP_VERSION = "1.0"
USER_KEY = 'default.username'
PASSWORD_KEY = 'default.password'
ASSIGNMENT_DELIMITER = '='
#from security_utils import decrypt

def add_key_val_to_dict(line, my_dict):
    string_list = line.split(ASSIGNMENT_DELIMITER, 1)
    key = str(string_list[0]).strip()
    value = str(string_list[1]).strip()
    my_dict[key] = value
    #print my_dict

def read_properties_file(file_path):
    if not os.path.exists(file_path):
        print file_path + " is missing "
        exit(1)

    result = dict()
    with open(file_path) as f:
        lines = f.readlines()

        for line in lines:
            line = line.strip()
            if not line.startswith("#") and ASSIGNMENT_DELIMITER in line:
                add_key_val_to_dict(line, result)

    return result

def update_log_table(filename, connection, scriptNum):
	try:
		query = """INSERT INTO script_tracking (script_num, sp_ver,script_name) VALUES ('%s','%s','%s')""" % (scriptNum,SP_VERSION, filename)
		print query
		cursor = connection.cursor()
		cursor.execute(query)
		print "%d rows were inserted" % cursor.rowcount 
		connection.commit()
		cursor.close()
	except Exception as e: 
		print(e)
		print "Failure in log insert  !! : "
		connection.rollback()
		cursor.close()





def check_if_prev_exists(filename ,connection, scriptNum):
	prev_script_num = 0

	if scriptNum % 10 == 0:
		prev_script_num = scriptNum -10
	else:
		prev_script_num = (scriptNum/10) *10


	check_exists_sql = "SELECT * FROM script_tracking  WHERE SCRIPT_NUM  = " +  str(prev_script_num) + " And sp_ver =" + SP_VERSION
	cursor = connection.cursor()
	cursor.execute(check_exists_sql)
	if cursor.rowcount == 0:
		print "Previous script number : " + str(prev_script_num) + " of current script " +  filename + " does not exist - scripts manager stopped"
		exit()

def check_if_next_exists(filename, connection, scriptNum):
	if scriptNum % 10 == 0:
		next_script_num = scriptNum + 10
	else:
		next_script_num = (scriptNum + 10) - (scriptNum % 10)

	check_exists_sql = "SELECT * FROM script_tracking  WHERE SCRIPT_NUM  = " +  str(next_script_num) + " And sp_ver =" + SP_VERSION
	cursor = connection.cursor()
	cursor.execute(check_exists_sql)
	if cursor.rowcount == 0:
		print "Next script number : " + str(next_script_num) + " of current script " +  filename + " does not exist - scripts manager stopped"
		return False
	else:
		print "Next script number : " + str(next_script_num) + " of current script " + filename + " exists"
		return True

def exec_sql_file(sql_file,connection, scriptNum):
	print "\n[INFO] Executing SQL script file: '%s'" % (sql_file)
	check_exists_sql = "SELECT * FROM script_tracking  WHERE SCRIPT_NUM  = " +  str(scriptNum) + " And sp_ver =" + SP_VERSION
	cursor = connection.cursor()
	cursor.execute(check_exists_sql)
	if  cursor.rowcount == 0:
		if (scriptNum % 10 != 0) and check_if_next_exists(sql_file, connection, scriptNum):
			cursor.close()
			return
		statement = ""
		for line in open(sql_file):
			if re.match(r'--', line):  # ignore sql comment lines
				continue
			if not re.search(r'[^-;]+;', line):  # keep appending lines that don't end in ';'
				statement = statement + line
			else:  # when you get a line ending in ';' then exec statement and reset for next statement
				statement = statement + line
				#print "\n\n[DEBUG] Executing SQL statement:\n%s" % (statement)
				try:
					cursor.execute(statement)
				except MySQLdb.Error, e:
					print "\n[ERROR] Error during execute script rolling back'%s'" % (str(e.args))
					connection.rollback()
					cursor.close()
					connection.close()
					exit(1)
				statement = ""
		connection.commit()
		cursor.close()
		update_log_table(sql_file, connection, scriptNum)
	else:
		print "script " +  sql_file + "  Allready run"
	cursor.close()

def create_script_tracker_table(filename, connection,tableName):
	stmt = "SHOW TABLES LIKE '" +  tableName + "'";
	cursor = connection.cursor()
	cursor.execute(stmt)
	result = cursor.fetchone()
	if  result == None:
		try:
			file = open(filename, 'r')
			sql = s = " ".join(file.readlines())
			print "Start executing: " + filename
			cursor = connection.cursor()
			cursor.execute(sql)
			connection.commit()
			update_log_table(filename, connection, 10)
			cursor.close()
		except:
			print "table exsists: " + filename
			connection.rollback()
			cursor.close()

def main(argv):
	totalArgs = len(sys.argv)
 
	if totalArgs < 2 :
		print  "Script Error!! you need add the play configuration file as parameter! "
		exit(1)

	filepath =sys.argv[1]
	conf_path = os.path.dirname(filepath)

	sqlFilesDir = os.path.join(os.path.join(conf_path,'sql'),'scripts')
	print sqlFilesDir
        
	props = read_properties_file(filepath)
	user_str = props.get(USER_KEY)
	password_str = props.get(PASSWORD_KEY)
	#password_str = decrypt(props[PASSWORD_KEY])
	#print "user = " + user_str + " --- " + password_str.strip('"')

	connection = MySQLdb.connect('localhost',user_str.strip('"') , password_str.strip('"'), 'postmansdb')

	#create_script_tracker_table(os.path.join(sqlFilesDir,"00010_create_scripts_track_table.sql"),connection,"script_tracking")
	path = os.getcwd()
	files = []
	for file in os.listdir(sqlFilesDir):
		if file.endswith(".sql"):
			if  re.search('(\d{1,4})\_', file) == None:
				print "File " + file + " is not in the required format will not execute !!  "
				continue
			files.append(file)
	##numPattern = re.compile('_(\d{1,4})\.', re.IGNORECASE)
	##files.sort(cmp, key=lambda sFile:
		##int(numPattern.search(sFile).group(1)))

	for sqlFile in sorted(files):

		myToken = sqlFile[ : 5]

		##lastToken = myToken[len(myToken)-1]
		scriptNum = int(myToken)

		sqlfilepath = os.path.join(sqlFilesDir,sqlFile)
		#print sqlfilepath
		if scriptNum != 10:
			check_if_prev_exists(sqlfilepath,connection, scriptNum)
		exec_sql_file(sqlfilepath, connection, scriptNum)
	connection.close()

if __name__ == "__main__":
    main(sys.argv[1:])
