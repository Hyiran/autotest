;~  弹出框提示

	Do
;~ 		
		If(WinExists("[CLASS:#32770]","注册成功")) Then
			
			
			WinSetOnTop("[CLASS:#32770]", "注册成功", 1)
			WinActivate( "[CLASS:#32770]","注册成功"  )
			;~MsgBox(0, "", "记事本窗口存在")
			$title = WinGetTitle("[CLASS:#32770]", "注册成功")

		EndIf
		
	Until WinActive("[CLASS:#32770]") 
;~ 	WinWaitActive("[CLASS:#32770]", "", 1)<>0 

	ControlClick("[CLASS:#32770]","","[CLASS:Button; INSTANCE:1]")

	
	
	
	
	
