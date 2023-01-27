package ir.wy.wycore.behind.proxy

interface ProxyFactory {
    fun <T> getProxy(proxyClass: Class<T>): T
}