/**
 * 
 */
  function test(){
	  alert("index.jsの読み込みに成功");
  }

"use strict";
  function OnSelectChange() {
    selindex = document.getElementById("SelectBox").selectedIndex;
    if(!selindex==0)	
    	document.location.href = "/?select_id="+selindex;
  }
