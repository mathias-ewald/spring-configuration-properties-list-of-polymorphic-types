package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.AbstractBindHandler;
import org.springframework.boot.context.properties.bind.BindContext;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;

public class CustomBindHandler extends AbstractBindHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(CustomBindHandler.class);
    
    private final Map<String, Class<? extends SelectorProperties>> configClasses = new HashMap<>();

    
    public CustomBindHandler(BindHandler parent) {
        super(parent);
        configClasses.put("LabelSelector", LabelSelectorProperties.class);
        configClasses.put("CompositeSelector", CompositeSelectorProperties.class);
    }

    @Override
	public <T> Bindable<T> onStart(ConfigurationPropertyName name, Bindable<T> target, BindContext context) {
		return super.onStart(name, target, context);
	}

	@Override
    public Object onSuccess(ConfigurationPropertyName name, Bindable<?> target, BindContext context, Object result) {
    	if (result instanceof SelectorProperties) {
        	SelectorProperties config = ((SelectorProperties) result);
            if (configClasses.containsKey(config.getType())) {
                Class<? extends SelectorProperties> aClass = configClasses.get(config.getType());
                BindResult<? extends SelectorProperties> r = context.getBinder().bind(name, Bindable.of(aClass));
                System.out.println(name + " => " + aClass.getCanonicalName());
                return r.get();
            } else {
                logger.error("unable to bind type {}", config.getType());
            }
        }
        return super.onSuccess(name, target, context, result);
    }
}
