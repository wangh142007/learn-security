package com.hao.learnsecurity.common.exceptions;

/**
 * @author ：Wang Hao
 * @date ：Created in 2020/12/4 16:32
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -3586828184536704147L;
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}