package org.dgac.app.web.util;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;


public class FacesAccesor  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Entrega una representación de la invocación de un método de un <i>Managed Bean</i>.
	 * 
	 * @param valueExpression
	 * @param expectedReturnType
	 * @param expectedParamTypes
	 * @return La representación de un método.
	 */
    public static MethodExpression createMethodExpression(String valueExpression,
            Class<?> expectedReturnType,
            Class<?>[] expectedParamTypes) {
        MethodExpression methodExpression = null;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExpressionFactory factory = fc.getApplication().getExpressionFactory();
            methodExpression = factory.
                    createMethodExpression(fc.getELContext(), valueExpression, expectedReturnType, expectedParamTypes);
        } catch (Exception e) {
            throw new FacesException("Method expression '" + valueExpression + "' could not be created.");
        }

        return methodExpression;
    }

    /**
     * Entrega una representación de la invocación de un <i>Action Listener</i> de un <i>Managed Bean</i>.
     * 
     * @param valueExpression
     * @param expectedReturnType
     * @param expectedParamTypes
     * @return La representación de un <i>Action Listener</i>.
     */
    public static MethodExpressionActionListener createMethodActionListener(String valueExpression,
            Class<?> expectedReturnType,
            Class<?>[] expectedParamTypes) {
        MethodExpressionActionListener actionListener = null;
        try {
            actionListener = new MethodExpressionActionListener(createMethodExpression(
                    valueExpression, expectedReturnType, expectedParamTypes));
        } catch (Exception e) {
            throw new FacesException("Method expression for ActionListener '" + valueExpression
                    + "' could not be created.");
        }

        return actionListener;
    }
    
    /**
     * Entrega la instancia de un <i>Managed Bean</i>.
     * 
     * @param beanName Nombre del <i>Managed Bean</i>.
     * @return La instancia del <i>Managed Bean</i>.
     */
    public static Object getManagedBean(final String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object bean;
        
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);
        }

        if (bean == null) {
            throw new FacesException("Managed bean with name '" + beanName
                + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
        }

        return bean;
    }
}
