/**
 * 
 */
  function test(){
	  alert("index.jsの読み込みに成功");
  }

"use strict";
  function OnSelectChange() {
	alert("invoke");
    selindex = document.getElementById("SelectBox").selectedIndex;
    document.location.href = "/"+selindex;
    
  }