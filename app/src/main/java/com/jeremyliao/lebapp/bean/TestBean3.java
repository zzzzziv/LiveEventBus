package com.jeremyliao.lebapp.bean;

import com.ziv.liveeventbus.ipc.annotation.IpcConfig;
import com.ziv.liveeventbus.processor.gson.GsonProcessor;

@IpcConfig(processor = GsonProcessor.class)
public class TestBean3 {
    public String content;

    @Override
    public String toString() {
        return content;
    }
}
