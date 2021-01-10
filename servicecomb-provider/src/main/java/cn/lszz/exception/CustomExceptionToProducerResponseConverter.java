package cn.lszz.exception;

import cn.lszz.utils.Result;
import org.apache.servicecomb.swagger.invocation.Response;
import org.apache.servicecomb.swagger.invocation.SwaggerInvocation;
import org.apache.servicecomb.swagger.invocation.exception.ExceptionToProducerResponseConverter;

public class CustomExceptionToProducerResponseConverter implements ExceptionToProducerResponseConverter<Throwable> {
    @Override
    public Class<Throwable> getExceptionClass() {
        // 返回IllegalStateException表示该converter处理IllegalStateException类型的异常
        return Throwable.class;
    }
    @Override
    public int getOrder() {
        // 返回的order值越小，优先级越高
        return 100;
    }
    @Override
    public Response convert(SwaggerInvocation swaggerInvocation, Throwable e) {
        // 这里是处理异常的逻辑
//        IllegalStateErrorData data = new IllegalStateErrorData();
//        data.setId(500);
//        data.setMessage(e.getMessage());
//        data.setState(e.getMessage());
//        InvocationException state = new InvocationException(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR, data);

        Result<Object> result = new Result<>(3000, "see you tomorrow", null);
        Response response = Response.create(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR, result);
        return response;
    }
}
