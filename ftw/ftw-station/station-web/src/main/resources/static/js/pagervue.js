var vuepager =
{
    template: '<ul class="pagination"><li><span>总计：{{total}}</span></li><li v-show="current != 1" v-on:click="current-- && vm.goto(current)" ><span><span aria-hidden="true">上一页</span></span></li><li v-for="index in pages" v-on:click="vm.goto(index)" :class="{active:current == index}" :key="index"><span>{{index}} <span class="sr-only">(current)</span></span></li><li v-show="totalpage != current && totalpage != 0 " v-on:click="current++ && vm.goto(current++)"><span><span aria-hidden="true">下一页</span></span></li></ul>',
    data: function () {
        return {
            current: 1,
            showPageItem:10//显示页码项的数目，
        }
    },
    props: ["totalpage", "pagesize", "current", "total"],
    computed: {
        pages: function () {
            
            var pag = [];
            if (this.totalpage == 0)
                return pag;
            if (this.current < this.showPageItem) { //如果当前的激活的项 小于要显示的条数
                //总页数和要显示的条数那个大就显示多少条
                var i = Math.min(this.showPageItem, this.totalpage);
                while (i) {
                    pag.unshift(i--);
                }
            } else { //当前页数大于显示页数了
                var middle = this.current - Math.floor(this.showPageItem / 2);//从哪里开始
                var i = this.showPageItem;
                if (middle > (this.totalpage - this.showPageItem)) {
                    middle = (this.totalpage - this.showPageItem) + 1
                }
                while (i--) {
                    pag.push(middle++);
                }
            }
            return pag
        }
    }
}