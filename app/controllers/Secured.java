package controllers;

import models.User;
import org.springframework.context.annotation.Scope;
import play.cache.Cache;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by saeed on 1/6/16.
 */

@Named
@Scope("prototype")
public class Secured extends Security.Authenticator {

    public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";

    @Inject
    private User user;

    @Override
    public String getUsername(Http.Context ctx) {
        User user = null;
        String[] authTokenHeaderValues = ctx.request().headers().get(AUTH_TOKEN_HEADER);
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            user = (User) Cache.get(authTokenHeaderValues[0]);
            if (user != null) {
                ctx.args.put("user", user);
                return user.getUserName();
            }
        }

        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized();
    }
}