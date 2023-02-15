package ir.wy.wycore.proxy.behind

interface ProxyFactory {
    fun <T> getProxy(proxyClass: Class<T>): T
}