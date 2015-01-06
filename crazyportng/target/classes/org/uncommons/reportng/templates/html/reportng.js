function toggleElement(elementId, displayStyle)
{
    var current = getStyle(elementId, 'display');
    document.getElementById(elementId).style.display = (current == 'none' ? displayStyle : 'none');
}


function getStyle(elementId, property)
{
    var element = document.getElementById(elementId);
    return element.currentStyle ? element.currentStyle[property]
           : document.defaultView.getComputedStyle(element, null).getPropertyValue(property);
}


function toggle(toggleId)
{
    var toggle;
    if (document.getElementById)
    {
        toggle = document.getElementById(toggleId);
    }
    else if (document.all)
    {
        toggle = document.all[toggleId];
    }
    toggle.textContent = toggle.innerHTML == '\u25b6' ? '\u25bc' : '\u25b6';
}
//隐藏用例步骤
function methodDescriptionclick(tid){
	  $("#"+tid).toggle(500);
}

//生成testoutput ID
//8 character ID (base=2) 
//uuid(8, 2)  //  "01001010" 
//// 8 character ID (base=10) 
//uuid(8, 10) // "47473046" 
//// 8 character ID (base=16) 
//uuid(8, 16) // "098F4D35" 
function uuid(len, radix) { 
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split(''); 
    var uuid = [], i; 
    radix = radix || chars.length; 
  
    if (len) { 
      // Compact form 
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix]; 
    } else { 
      // rfc4122, version 4 form 
      var r; 
  
      // rfc4122 requires these characters 
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-'; 
      uuid[14] = '4'; 
  
      // Fill in random data.  At i==19 set the high bits of clock sequence as 
      // per rfc4122, sec. 4.1.5 
      for (i = 0; i < 36; i++) { 
        if (!uuid[i]) { 
          r = 0 | Math.random()*16; 
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r]; 
        } 
      } 
    } 
  
    return uuid.join(''); 
} 



