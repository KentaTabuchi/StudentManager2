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
	  alert(select);
	  alert(text);
  }
"use strict";
  function OnSelectChange() {
    selindex = document.getElementById("SelectBox").selectedIndex;
    if(!selindex==0)	
    	document.location.href = "/?select_id="+selindex;
  }

