package controllers;

import play.Logger;
import play.cache.Cached;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by saeed on 1/7/16.
 */
public class VerboseAction extends Action<Log> {

    public F.Promise<Result> call(Http.Context ctx) throws Throwable {

        String logType = ((Log)this.configuration).logType();
        ctx.request().body().asJson();

        Logger.info("Calling action for " + ctx);

        F.Promise<Result> result = null;

        try {
            result = delegate.call(ctx);
            result.get(1000l).toScala().body();
        } catch (Throwable throwable) {

        }


        return result;
    }

}