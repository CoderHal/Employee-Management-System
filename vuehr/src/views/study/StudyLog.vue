<template>
    <div class="small">

        <canvas id="myChart2" width="800x" height="600px"></canvas>
    </div>
</template>
<script>
    import Chart from 'chart.js';



    export default {
        components: {

        },
        data() {
            return {

            }
        },
        mounted() {


            var ctx2 = document.getElementById("myChart2");

            var myChart2 = new Chart(ctx2, {
                type: "line",
                data: {
                    labels: ["第零天","第一天", "第二天", "第三天", "第四天", "第五天", "第六天", "第七天", "第八天"],
                    datasets: [
                        {
                            label: "学习时间",
                            backgroundColor: "rgba(225,10,10,0.3)",
                            borderColor: "rgba(225,103,110,1)",
                            borderWidth: 3,
                            pointStrokeColor: "#fff",
                            pointStyle: "crossRot",
                            data: [0, 5, 3, 4, 3, 3, 8, 4, 5],
                            cubicInterpolationMode: "monotone",
                            spanGaps: "false",
                            fill: "false"
                        }
                    ]
                },
                options: {

                }

            });
        },
        methods: {

        }
    }
</script>
<style>
    .small {
        width: 500px;
        height: 500px;
    }
</style>