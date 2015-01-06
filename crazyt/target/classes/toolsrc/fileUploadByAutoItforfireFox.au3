

	Do
;~ 		MsgBox(0, "", "定位文件上传对话框")
		If(WinExists("[CLASS:#32770]","文件名")) Then
			WinActivate( "[CLASS:#32770]","文件名"  )
		

		EndIf
		
	Until WinWaitActive("[CLASS:#32770]","文件名", 1)<>0 

	ControlClick("","",1001,"left","1","350","11")

	
		Sleep(1000)
;~ 		MsgBox(0, "", $CmdLine[1])
	ControlSetText("","","",$CmdLine[1])

	Sleep(1000)
	Send("{ENTER}")
	ControlSetText("","",1148,$CmdLine[2])
	Sleep(1000)
	ControlClick("","",1)
	
	
	
	
