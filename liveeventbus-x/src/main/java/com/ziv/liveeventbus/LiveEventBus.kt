package com.ziv.liveeventbus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ziv.liveeventbus.core.Config
import com.ziv.liveeventbus.core.LiveEvent
import com.ziv.liveeventbus.core.LiveEventBusCore
import com.ziv.liveeventbus.core.Observable
import com.ziv.liveeventbus.core.ObservableConfig

/**
 * _     _           _____                _  ______
 * | |   (_)         |  ___|              | | | ___ \
 * | |    ___   _____| |____   _____ _ __ | |_| |_/ /_   _ ___
 * | |   | \ \ / / _ \  __\ \ / / _ \ '_ \| __| ___ \ | | / __|
 * | |___| |\ V /  __/ |___\ V /  __/ | | | |_| |_/ / |_| \__ \
 * \_____/_| \_/ \___\____/ \_/ \___|_| |_|\__\____/ \__,_|___/
 *
 *
 *
 * Created by liaohailiang on 2019/1/21.
 */
object LiveEventBus {
    /**
     * get observable by key with type
     *
     * @param key String
     * @param type Class
     * @param <T> T
     * @return Observable
    </T> */
    @JvmStatic
    fun <T> get(key: String, type: Class<T>): Observable<T> {
        return LiveEventBusCore.get().with(key, type)
    }

    /**
     * get observable by key
     *
     * @param key String
     * @param <T> T
     * @return Observable
    </T> */
    @JvmStatic
    fun <T> get(key: String): Observable<T> {
        return get(key, Any::class.java) as Observable<T>
    }

    /**
     * get observable from eventType
     *
     * @param eventType Class
     * @param <T> T
     * @return Observable
    </T> */
    @JvmStatic
    fun <T : LiveEvent?> get(eventType: Class<T>): Observable<T> {
        return get(eventType.name, eventType)
    }


    /**
    首先使用内部类 Config 设置 params，
    调用 config 获取 Config 实例，
    然后调用 Config 的方法配置 LiveEventBus，
    在 Application.onCreate 中调用该方法
     * @return Config
     */
    @JvmStatic
    fun config(): Config {
        return LiveEventBusCore.get().config()
    }

    /**
 * 使用内部类 Config 设置 params
     * 首先调用 Config 获取 Config 实例
     * 然后，调用 Config 的方法配置 LiveEventBus
     * 在 Application.onCreate 中调用该方法
     * @param key String
     * @return ObservableConfig
     */
    fun config(key: String): ObservableConfig {
        return LiveEventBusCore.get().config(key)
    }

    /**
     * 推送消息
     */
    fun post(liveEvent: LiveEvent){
        get(liveEvent.javaClass).post(liveEvent)
    }

    /**
     * 推送消息-跨进程
     */
    fun postAcrossProcess(liveEvent: LiveEvent){
        get(LiveEvent::class.java).postAcrossProcess(liveEvent)
    }

    /**
     * 推送消息-跨App
     */
    fun postAcrossApp(liveEvent: LiveEvent){
        get(LiveEvent::class.java).postAcrossApp(liveEvent)
    }

    /**
     * 推送消息-延迟
     */
    fun postAcrossApp(liveEvent: LiveEvent,long: Long){
        get(LiveEvent::class.java).postDelay(liveEvent,long)
    }

    /**
     * 订阅消息-如果之前有消息发送，可以在注册时收到消息（消息同步）
     */
    inline fun <reified T : LiveEvent?> observeSticky(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        return get(T::class.java).observeSticky(lifecycleOwner,observer)
    }
    /**
     * 订阅消息
     */
    inline fun <reified T : LiveEvent?> observe(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        return get(T::class.java).observe(lifecycleOwner,observer)
    }
}
