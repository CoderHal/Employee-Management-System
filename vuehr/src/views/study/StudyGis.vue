<template>
    <iframe src="https://osmbuildings.org/?lat=52.52079&lon=13.40825&zoom=15.8&tilt=30"
            width="900px" height="600px"
            scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true">
    </iframe>
</template>

<script>
    import * as L from 'leaflet'
    //Vue.L = Vue.prototype.$L = L

    var map;
    export default {
        name: 'HelloWorld',
        data () {
            return {
                msg: 'Welcome to Your Vue.js App'
            }
        },

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    #map {
        width: 100%;
        height: calc(100vh);
    }
</style>