package com.tester.restapi.enumeration;

	public enum ResponseCodes {
	    SUCCESS("01", "sukses"),
	    FAILED("02", "gagal"),
	    EXISTS("03", "data sudah ada"),
	    NOTFOUND("04", "data tidak ditemukan"),
		OTHER("99", "error");
		
	    public final String code;
	    public final String desc;
	 
	    private ResponseCodes(String code, String desc) {
	        this.code = code;
	        this.desc = desc;
	    }

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
}
