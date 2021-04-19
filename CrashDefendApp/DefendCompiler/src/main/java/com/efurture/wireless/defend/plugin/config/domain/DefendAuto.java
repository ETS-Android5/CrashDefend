package com.efurture.wireless.defend.plugin.config.domain;

import javassist.CtClass;
import javassist.CtMethod;

import java.io.Serializable;
import java.util.Map;

public class DefendAuto implements Serializable, MatchDefend {
    private static final long serialVersionUID = -3714160097375512785L;

    private String scope;
    private Map<String,String> attributes;

    public DefendAuto(String scope, Map<String, String> attributes) {
        this.scope = scope;
        this.attributes = attributes;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean isDefend(CtClass ctClass, CtMethod ctMethod) {
        if(ctClass.getName().startsWith(scope)){
            return DefendAutoConfig.isDefend(ctClass, ctMethod);
        }
        return false;
    }
}
