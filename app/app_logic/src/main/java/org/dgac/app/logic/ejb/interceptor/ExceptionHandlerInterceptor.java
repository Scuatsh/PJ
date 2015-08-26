package org.dgac.app.logic.ejb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.ApplicationContext;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;


public class ExceptionHandlerInterceptor  {
	
   /**
    * This method intercepts all call for server. If exception occurs while
    * processing server request, then exception is sent back to caller.
    *
    * @param invocationContext
    * @return Object
    * @throws Exception
    */
   @AroundInvoke
   public Object handleExceptions(final InvocationContext invocationContext) throws Exception {

       Object result = null;

       try {
           result = invocationContext.proceed();
       } catch (Exception e) {
    	   throw e;
       } 
       return result;
   }

	
	protected BeanFactory getBeanFactory() {
		String key = null;
		BeanFactoryReference ref = ContextSingletonBeanFactoryLocator.getInstance().useBeanFactory(key);
		BeanFactory factory = ref.getFactory();
		if (factory instanceof ApplicationContext) {
			factory = ((ApplicationContext) factory).getAutowireCapableBeanFactory();
		}
		return factory;
	}


}
