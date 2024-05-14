package backend.univfit.global.argumentResolver;

import backend.univfit.global.argumentResolver.customAnnotation.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Slf4j
public class MemberInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
        boolean hasUserType = MemberInfoObject.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String socialLoginInfo = webRequest.getAttribute("socialLoginInfo", RequestAttributes.SCOPE_REQUEST).toString();
        Long socialPK = Long.parseLong(webRequest.getAttribute("socialPK", RequestAttributes.SCOPE_REQUEST).toString());
        Object memberIdObj = webRequest.getAttribute("memberId", RequestAttributes.SCOPE_REQUEST);
        Long memberId = null;

        if(memberIdObj != null){
            memberId = Long.parseLong(memberIdObj.toString());
        }


        return MemberInfoObject.of(socialLoginInfo, socialPK, memberId);
    }
}
