public static Queue<String> extractElem(String name,String xml){
	  	   int i =0;
	  	   String begin = "<" + name;
	  	   String end = "</" + name+">";
	  	   int len = begin.length();
	  	   Queue<String> data = new LinkedList<String>();
	  	   Stack<String> names = new Stack<String>();
	  	   
	  	   for(int j=0; j< xml.length()-len;j++){
	  		  String seg = xml.substring(j,j+len);
	  		  if(seg.toUpperCase().equals(begin.toUpperCase()) ){
	  		      i = 1;
	  			  for(int k=j+1; k<xml.length()-len-2;k++){	  				
	  				 if(xml.charAt(k) == '<' && xml.charAt(k+1) != '/'){
	  					i += 1;
	  				    String cc ="";
	  					for(int n= k+1;n<k+100;n++){
	  						if(xml.charAt(n) ==' '){
	  							k = n;
	  							break;
	  						}
	  						cc += xml.charAt(n);
	  					}
	  					names.push(cc);
	  				 }else if(xml.substring(k,k+2).equals("/>")){
	  					if(i == 1){
	  						data.add(xml.substring(j,k+2));
	  						j = k;
	  						break;
	  					} 
	  					i -=1;
	  					names.pop();
	  				 }
	  				 else if(xml.charAt(k) == '<' && xml.charAt(k+1) == '/'){
	  					  String cur;
	  					  
	  					 if(names.size()>0){
	  						 cur = "</" +names.peek() + ">";
	  					 }else{
	  						 cur = end;
	  					 }
	  					int llen = cur.length();
	  					String cc =""; 
	  					for(int n= k+2;n<k+100;n++){
	  						if(xml.charAt(n) ==' ' || xml.charAt(n) =='>'){	  							
	  							break;
	  						}
	  						cc += xml.charAt(n);
	  					}
	  					
	  					 if(eqString(cc.trim().toUpperCase(),cur.toUpperCase()) ){
		  				    if(i == 1){
		  						data.add(xml.substring(j,k+llen+1));
		  						j = k;
		  						break;
		  					}
		  					i -=1;
		  					names.pop();
	  				    }
	  				}
	  			  }	  		  
	  		  }
	  	   }
	  	   return data;
	  	}

    private static boolean eqString(String sa,String sb){
    	if(sa.equals(sb)){
    		return true;
    	}else{
    		String na = sa.replace(" ", "").replace("<", "").replace(">", "").replace("/", "");
    		String nb = sb.replace(" ","").replace("<", "").replace(">", "").replace("/", "");
    		return na.equals(nb);
    	}    	
    }
  	public static Map<String,String> extractAttr(String xml,String elementName){
  	  	Map<String,String> attrs = new HashMap<String,String>();
  		String begin = '<' + elementName;
  		char[] split = {' ','\t','\r'};
  		if(xml.indexOf(begin) != 0){
  			return attrs;
  		}
  		for(int i=elementName.length() +1; i<xml.length(); i++){
  			String att ="";
  			String val ="";
  			int st  =0;
  			
  			for(int k=i;k<xml.length();k++){
  				if(xml.charAt(i) ==' ' || xml.charAt(i) =='\t' || xml.charAt(i) == '\r' ){
  				  i += 1;
  			    }else{
  					break;
  				}
  			}		
  			if(xml.charAt(i) =='>' || xml.substring(i,i+2).equals("/>")) break;
  			for(int k=i; k<xml.length();k++){
  				if(xml.charAt(i) != '='){
  				  att += xml.charAt(i);
  				  i += 1;
  			    }else{
  					break;
  				}
  			}
  			i += 2;
  			st += 1;
  			for(int k=i;k<xml.length();k++){
  				if(xml.charAt(i) != '\'' && xml.charAt(i) !='"'){
  					val +=xml.charAt(i);
  					i += 1;
  				}else{
  					break;
  				}			
  			}

  			String obj  = att +":" + val;
  				
  			if(st ==1)
  			  attrs.put(att,val);
  		}
  		return attrs;
  	}	
