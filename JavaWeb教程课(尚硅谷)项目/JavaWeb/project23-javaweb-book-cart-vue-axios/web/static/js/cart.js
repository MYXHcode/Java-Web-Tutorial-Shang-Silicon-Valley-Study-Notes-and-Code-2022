/**
 * 编辑当前用户的购物车中(Thymeleaf 页面调用此方法)
 */
function editCart(cartItemId, buyCount) {
    window.location.href = 'cart.do?operate=editCart&cartItemId=' + cartItemId + '&buyCount=' + buyCount;
}

window.onload = function () {
    let vue = new Vue({
        el: "#cart_div",

        data: {
            cart: {}
        },

        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "cartInfo"
                    }
                })
                    .then(function (value) {
                        let cart = value.data;

                        vue.cart = cart;

                        console.log(value.data);
                    })
                    .catch(function (reason) {
                        console.log(reason);
                    });
            },

            editCart: function (cartItemId, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        /**
                         * 编辑当前用户的购物车中(Vue 页面调用此方法)
                         */
                        operate: "editCartInfo",
                        cartItemId: cartItemId,
                        buyCount: buyCount
                    }
                })
                    .then(function (value) {
                        vue.getCart();

                        console.log(value.data);
                    })
                    .catch(function (reason) {
                        console.log(reason);
                    });
            }
        },

        mounted: function () {
            this.getCart();
        }
    })
}