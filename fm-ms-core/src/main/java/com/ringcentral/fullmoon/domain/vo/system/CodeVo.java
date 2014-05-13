package com.ringcentral.fullmoon.domain.vo.system;

import java.util.List;

/**
 * Created by huangking on 14-4-12.
 */
public class CodeVo {
    private String codeType;
    private String value;
    private String label;
    private List<CodeVo> childCodes;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CodeVo> getChildCodes() {
        return childCodes;
    }

    public void setChildCodes(List<CodeVo> childCodes) {
        this.childCodes = childCodes;
    }
}
