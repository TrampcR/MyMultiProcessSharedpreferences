# MyMultiProcessSharedpreferences

### Demo 使用

![](https://i.loli.net/2019/11/27/UowakHTZjLW2e8J.png)

![](https://i.loli.net/2019/11/27/QF87kr9VCcElsSA.png)



根据以上两张图片序号操作，会打印如下日志：

```Java
MainActivity onCreate: processName = com.trampcr.mymultiprocesssharedpreferences:process1
SecondActivity onCreate: processName = com.trampcr.mymultiprocesssharedpreferences:process2
get sp name = trampcr, processName = com.trampcr.mymultiprocesssharedpreferences:process2
```

简单解释一下，这里会在 :process1 进程保存一个 name=trampcr，在 :process2 进程进行获取，如果获取到说明这种方案可行。
