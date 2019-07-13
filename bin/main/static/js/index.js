//|---------------------------------------------------------------------
//|This script is called by "index.html".This cannot use the other pages.
//|---------------------------------------------------------------------
  function test(){
	  alert("index.jsの読み込みに成功?");
  }
  function OnButton1Click(){
	  select = document.getElementById("FindBox").selectedIndex;
	  text = document.getElementById("FindText").value;
	  url = "/?find_text="+text+"&find_select="+select;
	  document.location.href = url;
  }
  function OnButton2Click(){
	  document.location.href = "/?select_id="+1; // 1 means find all by id.
  }
"use strict";
  function OnSelectChange() {
    selindex = document.getElementById("SelectBox").selectedIndex;
    if(!selindex==0)	
    	document.location.href = "/?select_id="+selindex;
  }

