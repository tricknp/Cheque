package util;

public enum ChequeStatusEnum {
    
     COMPENSADO(0), NAO_COMPENSADO(1), SEM_FUNDO(2);
        
        private final int status;
        
        ChequeStatusEnum(int status) {
            this.status = status;
        }
    
    
}
