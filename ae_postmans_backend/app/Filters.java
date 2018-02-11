import javax.inject.Inject;
import javax.inject.Singleton;

import filters.ExampleFilter;
import play.Environment;
import play.Logger;
import play.Mode;
import play.filters.cors.CORSFilter;
import play.filters.headers.SecurityHeadersFilter;
import play.http.DefaultHttpFilters;
import play.mvc.EssentialFilter;

/**
 * This class configures filters that run on every request. This
 * class is queried by Play to get a list of filters.
 *
 * Play will automatically use filters from any class called
 * <code>Filters</code> that is placed the root package. You can load filters
 * from a different class by adding a `play.http.filters` setting to
 * the <code>application.conf</code> configuration file.
 */
@Singleton
public class Filters extends DefaultHttpFilters {

    private final Environment env;
    //private final EssentialFilter exampleFilter;
    private CORSFilter corsFilter;
    
    /**
     * @param env Basic environment settings for the current application.
     * @param exampleFilter A demonstration filter that adds a header to
     */
    @Inject
    public Filters(Environment env, ExampleFilter exampleFilter, SecurityHeadersFilter securityHeadersFilter, CORSFilter corsFilter) {
        super(securityHeadersFilter, corsFilter);
        this.env = env;
        this.corsFilter = corsFilter;
        //this.exampleFilter = exampleFilter;
    }

    @Override
    public EssentialFilter[] filters() {
      // Use the example filter if we're running development mode. If
      // we're running in production or test mode then don't use any
      // filters at all.
      /*if (env.mode().equals(Mode.DEV)) {
          return new EssentialFilter[] { exampleFilter };
      } else {
         return new EssentialFilter[] {};
      }
      */
    	Logger.info(corsFilter.asJava().toString());
    	Logger.info(env.mode().name());

    	return new EssentialFilter[] {corsFilter.asJava() };

    }

}
