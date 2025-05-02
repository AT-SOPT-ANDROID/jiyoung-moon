package org.sopt.at.ui.home.viewmodel

import androidx.lifecycle.ViewModel

class ContentViewModel : ViewModel() {
    // 최상단 배너뷰
    val bannerContents = listOf<Content>(
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/LTqPPE4H7UzsivfVThSbFgUSZG5iYrkw-leA8onwK5hXsyORRU1h93rA1Vvdoyx4VRuqhPp1CvHaLAktdMdo5g4U1DZghKXAAAHmjskCUZo1jEngMq1F3WAeqV__JB-tHXXegJCrTu9RME3Dt57wVA.webp",
            title = "바니와 오빠들"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/eZLEBPFQzBjnaEqvSp2YYraNFIjldjvtzEsoEvOZlN7iP2wWvJJ8zpRxGxlyWTdBbQz11vjyKJd83DQYw4xk-2ggiUYk9DI3Sm3Io8R7_DGhPsYD3s14G9M2JH7jpYHuaDwjNwaykuVvKavwxJFTGw.webp",
            title = "언니네 산지직송2"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/2RK5r4xio4vhANrlR7ivBgUpQn_GdrmvocjotGZ_ljdlRDYfNq40SN1GVSLI8cry5mhCkIWWzGKhVb4EplYH-9ShZC7YGF1d_c5f7DMVL2jGMalRHPDndJuL0KO7HEK61oAWv4lvMCZaVw-O9qNhIw.webp",
            title = "지구오락실3"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/gWduQEKQnoYUOZBwdRb1YjtDX3LKN-6JOQ20j_AJAQCdxKg6ND8x_IyKouHE0V6Bm5c-t8jdPWeVRdrTC6EWpssYfgrqa5PKCHEk0EwpmasGp5kFqvftCoVH8eCr7nKm9fA3GQWnC4YjH1aNPLf8Fw.webp",
            title = "신병3"
        )
    )

    // 오늘의 티빙 20
    val top20Contents = listOf<Content>(
        Content(
            ranking = 1,
            imgUrl = "https://i.namu.wiki/i/gWduQEKQnoYUOZBwdRb1YjtDX3LKN-6JOQ20j_AJAQCdxKg6ND8x_IyKouHE0V6Bm5c-t8jdPWeVRdrTC6EWpssYfgrqa5PKCHEk0EwpmasGp5kFqvftCoVH8eCr7nKm9fA3GQWnC4YjH1aNPLf8Fw.webp",
            title = "신병3"
        ),
        Content(
            ranking = 2,
            imgUrl = "https://i.namu.wiki/i/i4fjKpVKa-hJTnklTkjgE_TkTxs71DLzeAkiUp5RVZ36d405sJHesA2J4UosxezweG8K1k_4ffYPoGpiy_djA-QB9j8Ej-88xDIaKKXirCDC67tUHkVUvNGlZifDqfs_0O6KoETBHmh3-QOvryvB8A.webp",
            title = "유 퀴즈 온 더 블럭"
        ),
        Content(
            ranking = 3,
            imgUrl = "https://i.namu.wiki/i/2RK5r4xio4vhANrlR7ivBgUpQn_GdrmvocjotGZ_ljdlRDYfNq40SN1GVSLI8cry5mhCkIWWzGKhVb4EplYH-9ShZC7YGF1d_c5f7DMVL2jGMalRHPDndJuL0KO7HEK61oAWv4lvMCZaVw-O9qNhIw.webp",
            title = "지구오락실3"
        ),
        Content(
            ranking = 4,
            imgUrl = "https://i.namu.wiki/i/eZLEBPFQzBjnaEqvSp2YYraNFIjldjvtzEsoEvOZlN7iP2wWvJJ8zpRxGxlyWTdBbQz11vjyKJd83DQYw4xk-2ggiUYk9DI3Sm3Io8R7_DGhPsYD3s14G9M2JH7jpYHuaDwjNwaykuVvKavwxJFTGw.webp",
            title = "언니네 산지직송2"
        ),
        Content(
            ranking = 5,
            imgUrl = "https://i.namu.wiki/i/LTqPPE4H7UzsivfVThSbFgUSZG5iYrkw-leA8onwK5hXsyORRU1h93rA1Vvdoyx4VRuqhPp1CvHaLAktdMdo5g4U1DZghKXAAAHmjskCUZo1jEngMq1F3WAeqV__JB-tHXXegJCrTu9RME3Dt57wVA.webp",
            title = "바니와 오빠들"
        ),
    )

    // 지금 방영 중인 콘텐츠
    val nowOnContents = listOf<Content>(
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/LTqPPE4H7UzsivfVThSbFgUSZG5iYrkw-leA8onwK5hXsyORRU1h93rA1Vvdoyx4VRuqhPp1CvHaLAktdMdo5g4U1DZghKXAAAHmjskCUZo1jEngMq1F3WAeqV__JB-tHXXegJCrTu9RME3Dt57wVA.webp",
            title = "바니와 오빠들"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/2RK5r4xio4vhANrlR7ivBgUpQn_GdrmvocjotGZ_ljdlRDYfNq40SN1GVSLI8cry5mhCkIWWzGKhVb4EplYH-9ShZC7YGF1d_c5f7DMVL2jGMalRHPDndJuL0KO7HEK61oAWv4lvMCZaVw-O9qNhIw.webp",
            title = "지구오락실3"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/eZLEBPFQzBjnaEqvSp2YYraNFIjldjvtzEsoEvOZlN7iP2wWvJJ8zpRxGxlyWTdBbQz11vjyKJd83DQYw4xk-2ggiUYk9DI3Sm3Io8R7_DGhPsYD3s14G9M2JH7jpYHuaDwjNwaykuVvKavwxJFTGw.webp",
            title = "언니네 산지직송2"
        ),
        Content(
            ranking = 0,
            imgUrl = "https://i.namu.wiki/i/gWduQEKQnoYUOZBwdRb1YjtDX3LKN-6JOQ20j_AJAQCdxKg6ND8x_IyKouHE0V6Bm5c-t8jdPWeVRdrTC6EWpssYfgrqa5PKCHEk0EwpmasGp5kFqvftCoVH8eCr7nKm9fA3GQWnC4YjH1aNPLf8Fw.webp",
            title = "신병3"
        )
    )
}