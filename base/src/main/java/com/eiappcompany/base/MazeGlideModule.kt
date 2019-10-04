package com.eiappcompany.base

@GlideModule(glideName = "MazeGlide")
@Excludes(value = [OkHttpLibraryGlideModule::class])
class MazeGlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient.Builder()
            .readTimeout(DEFAULT_TIMEOUT, SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, SECONDS)
            .build()

        val factory = OkHttpUrlLoader.Factory(client)

        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeBytes = DEFAULT_MEMORY_CACHE_SIZE
        builder.apply {
            setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
            setDiskCache(InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes.toLong()))
            setDefaultRequestOptions(requestOptions())
        }
    }

    private fun requestOptions(): RequestOptions {
        return RequestOptions().apply {
            signature(
                ObjectKey(
                    System.currentTimeMillis() / (DEFAULT_SIGNATURE_OBJECT)
                )
            ) // 1 week cache
            centerCrop()
            dontAnimate()
            override(SIZE_ORIGINAL)
            encodeFormat(CompressFormat.PNG)
            encodeQuality(DEFAUKT_ENCODE_QUALITY)
            diskCacheStrategy(RESOURCE)
            format(PREFER_ARGB_8888)
            skipMemoryCache(true)
        }
    }

    companion object {
        private const val DEFAULT_TIMEOUT = 60L
        private const val DEFAULT_MEMORY_CACHE_SIZE = 1024 * 1024 * 300 // 300mb cache
        private const val DEFAULT_SIGNATURE_OBJECT = 168 * 60 * 60 * 1000
        private const val DEFAUKT_ENCODE_QUALITY = 100
    }
}
