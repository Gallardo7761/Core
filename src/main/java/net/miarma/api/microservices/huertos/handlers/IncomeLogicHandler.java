package net.miarma.api.microservices.huertos.handlers;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import net.miarma.api.common.Constants;
import net.miarma.api.common.http.ApiStatus;
import net.miarma.api.util.EventBusUtil;
import net.miarma.api.util.JsonUtil;

public class IncomeLogicHandler {
	private final Vertx vertx;
	
	public IncomeLogicHandler(Vertx vertx) {
		this.vertx = vertx;
	}
	
	public void getMyIncomes(RoutingContext ctx) {
		String token = ctx.request().getHeader("Authorization").substring("Bearer ".length());
		JsonObject request = new JsonObject().put("action", "getMyIncomes").put("token", token);
		vertx.eventBus().request(Constants.HUERTOS_EVENT_BUS, request, ar -> {
			if (ar.succeeded()) JsonUtil.sendJson(ctx, ApiStatus.OK, ar.result().body());
			else EventBusUtil.handleReplyError(ctx, ar.cause(), "Incomes not found");
		});
	}
}
