/**
 * 
 */
  function test(){
	  alert("index.jsの読み込みに成功");
  }


  function OnSelectChange() {
	alert("invoke");
    selindex = document.getElementById("SelectBox").selectedIndex;
    switch (selindex) {
      case 0:
        alert(0);
        document.location.href = "/"+selindex;
        break;
      case 1:
        alert(1);
        document.location.href = "/"+selindex;
        break;
      case 2:
        alert(2);
        break;

    }
  }