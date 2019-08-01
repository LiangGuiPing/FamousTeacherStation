$(function() {
    var i = [{
            menu: "家长大学",
            href: "#",
            child: [{
                menu: "文章",
                href: "/p/parentcollege/articlelist"
            }, {
                menu: "微课程",
                href: "/p/parentcollege/microcourselist"
            }, {
                menu: "家园共育",
                href: "/p/parentcollege/homecampuslist"
            }, {
                menu: "音频小课",
                href: "/p/parentcollege/audiolist"
            }]
        }, {
            menu: "ECA",
            href: "#",
            child: [{
                menu: "文章",
                href: "/p/eca/articlelist"
            }]
        }, {
            menu: "园所",
            href: "#",
            child: [{
                menu: "文章",
                href: "/p/campus/articlelist"
            }]
        }, {
            menu: "推荐管理",
            href: "/p/recommend/index",
            child: [{
                menu: "推荐列表",
                href: "/p/recommend/index"
            }]
        }],
        e = window.location.href.split("/"),
        a = e[e.length - 1].replace("#", ""),
        r = [];

    function h(e, n) {
        if (e)
            for (var l = 0; l < e.length; l++) {
                var a = e[l].child && 0 < e[l].child.length ? '<ul aria-expanded="true" class="menu-child indent">' + h(e[l].child, "") + "</ul>" : "";
                n += '<li><a href="' + e[l].href + '">' + e[l].menu + "</a>" + a + "</li>"
            }
        return n
    }

    function l(e, n) {
        for (var l = 0; l < e.length; l++) n += '<li><a href="' + e[l].href + '">' + e[l].menu + "</a></li>";
        return n
    }
    $(".nav-slider").append(function() {
        for (var e = "", n = 0; n < i.length; n++) {
            var l = i[n].child,
                a = l && 0 < l.length ? '<ul aria-expanded="false" class="menu-child">' + h(l, "") + "</ul>" : "";
            e += '<li class=""><a class="" href="' + i[n].href + '" aria-expanded="true"> <i class="glyphicon glyphicon-th-list"></i><span class="text">' + i[n].menu + '</span><i class="glyphicon glyphicon-menu-right icon-right"></i></a>' + a + "</li>"
        }
        return e
    }()), $(".topmenu").append(function() {
        for (var e = "", n = 0; n < i.length; n++) e += ' <li class="dropdown"><a href="' + i[n].href + '" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="false">' + i[n].menu + ' <span class="caret"></span></a><ul class="dropdown-menu">' + l(i[n].child, "") + "</ul></li>";
        return e
    }()), $(".nav-slider li").each(function(e, n) {
        if ($(n).find("a").attr("href") == a) {
            $(n).addClass("active").parents("li").addClass("active");
            var l = $(n).parents("li");
            r.push($(n).children("a").text()), l.each(function(e, n) {
                r.push($(n).children("a").text())
            })
        }
    }), r.reverse();
    for (var n = "", t = 0; t < r.length; t++) t < r.length - 1 ? n += '<li><a href=""><i class="glyphicon glyphicon-menu-right icon-arrow"></i>' + r[t] + "</a></li>" : n += '<li class="active"><i class="glyphicon glyphicon-menu-right icon-arrow"></i>' + r[t] + "</li>";
    $(".home-list").append(n), $("#menu").metisMenu({
        activeClass: "active"
    })
});