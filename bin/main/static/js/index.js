/**
 * 
 */
  function test(){
	  alert("index.jsの読み込みに成功?");
  }
  function OnButtonClick(){
	  alert("findButtonClick");
	  select = document.getElementById("FindBox").selectedIndex;
	  text = document.getElementById("FindText").value;
	  url = "/?find_text="+text+"&find_select="+select;
	  alert(url);
	  document.location.href = url;
	//	 @RequestParam(name="find_select,required=false")Integer find_select,
	//	 @RequestParam(name="find_text,requred=false")String find_text,
	  
  }
"use strict";
  function OnSelectChange() {
    selindex = document.getElementById("SelectBox").selectedIndex;
    if(!selindex==0)	
    	document.location.href = "/?select_id="+selindex;
  }

