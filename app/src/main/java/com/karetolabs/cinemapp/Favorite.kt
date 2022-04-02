package com.karetolabs.cinemapp

data class Favorite(
    var id:Long?,
    var title:String?,
    var urlImage:String?,
    var summary:String?,
    var year:String?,
    var genre:String?,
    var duration:String?,
    var uriImage:String? = null
)

class FavoriteProvider{
    companion object{
        val favorites = arrayListOf<Favorite>(
            Favorite(1L,
                "Matrix",
                "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "1999",
                "Sci-Fi",
                "120"),
            Favorite(2L,
                "Batman v Superman",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9lqKg-B14vhvfGuuy6Rj5NH7yrtGWiR2WvQ&usqp=CAU",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "2016",
                "Comic",
                "170"),
            Favorite(3L,"Inception",
                "https://c8.alamy.com/compes/2c4x052/inception-2010-dirigida-por-christopher-nolan-y-protagonizada-por-leonardo-dicaprio-joseph-gordon-levitt-ellen-page-tom-hardy-y-ken-watanabe-un-equipo-irnante-al-subconsciente-de-un-hombre-de-negocios-utilizando-la-tecnologia-de-compartir-suenos-para-una-planta-una-semilla-para-influir-en-su-decision-en-el-mundo-real-2c4x052.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "2010",
                "Aventura",
                "189"),
            Favorite(4L,
                "Matrix",
                "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "1999",
                "Sci-Fi",
                "120"),
            Favorite(5L,"Inception",
                "https://c8.alamy.com/compes/2c4x052/inception-2010-dirigida-por-christopher-nolan-y-protagonizada-por-leonardo-dicaprio-joseph-gordon-levitt-ellen-page-tom-hardy-y-ken-watanabe-un-equipo-irnante-al-subconsciente-de-un-hombre-de-negocios-utilizando-la-tecnologia-de-compartir-suenos-para-una-planta-una-semilla-para-influir-en-su-decision-en-el-mundo-real-2c4x052.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "2010",
                "Aventura",
                "189"),
            Favorite(6L,
                "Batman v Superman",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9lqKg-B14vhvfGuuy6Rj5NH7yrtGWiR2WvQ&usqp=CAU",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                "2016",
                "Comic",
                "170"),

        )
    }
}
