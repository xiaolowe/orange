!function(g, h) {
    function b(j) {
        var i, k = new RegExp("(^| )" + j + "=([^;]*)(;|$)");
        if (i = g[c[11]].cookie.match(k)) {
            return decodeURIComponent(i[2])
        } else {
            return null
        }
    }
    function d(i, j, l) {
        var k = new Date();
        isNaN(l) && (l = 3);
        k.setTime(k.getTime() + l * 24 * 60 * 60 * 1000);
        g[c[11]].cookie = i + "=" + encodeURIComponent(j) + "; path=/; expires=" + k.toGMTString()
    }
    var c = ["clientdownshow", "iClose", "smartDom", "openApp", "preventDefault", "stopPropagation", "mainBody", "floatApp", "style", "createElement", "append", "document", "class", "push"], f = g[c[11]], e = g.$, a = function() {
        this.calClose() || this.createHtml()
    };
    a.prototype = {constructor: a,calClose: function() {
        var i = b(c[0] + "_index");
        if (i) {
            this[c[1]] = !0;
            return this[c[1]]
        }
    },template: function() {
        var i = [];
        i[c[13]]('<div class="floatApp">');
        i[c[13]]('<a href="#box" class="linkbox" id="wapdsy_D05_04">');
        i[c[13]]('<span class="btn">\u7acb\u5373\u4e0b\u8f7d</span>');
        i[c[13]]('<img src="http://img2.soufun.com/wap/touch/img/sf-72.png" width="36">');
        i[c[13]]('<div class="text">');
        i[c[13]]('<p class="f14">\u6447\u5e78\u8fd0\u679c \u9001\u795d\u798f</p>');
        i[c[13]]('<p class="f14">\u5343\u5143\u5927\u793c \u7b49\u4f60\u62ff</p>');
        i[c[13]]('</div>');
        i[c[13]]('</a>');
        i[c[13]]('<a href="#off" class="off"><span>x</span></a>');
        i[c[13]]('</div>');
        return i.join("")
    },createHtml: function() {
        if (!this[c[1]]) {
            var l = this.template(), k = f[c[9]](c[8]), o = f[c[9]]("div"), n = [""];
            o.innerHTML = l, this[c[2]] = o.querySelector("div." + c[7]);
            var j = e(f.body);
            j[c[10]](this[c[2]]), this.listen();
        }
    },show: function() {
        this[c[1]] || this[c[2]] && (this[c[2]][c[8]].display = "block")
    },hide: function() {
        this[c[1]] || this[c[2]] && (this[c[2]][c[8]].display = "none")
    },listen: function() {
        if (!this[c[1]]) {
            var i = this, j = e(i[c[2]]);
            j.find("a.off").bind("click", function(k) {
                k[c[4]](), k[c[5]]();
                i.foo(), i.log(3)
            });
            j.find("a.linkbox").bind("click", function(o) {
                var n, k, m = "http://js.soufunimg.com/common_m/m_public/jslib/openapp/openapp.js", p = function(q) {
                    typeof g[c[3]] === "function" && (q = g[c[3]]);
                    var l = q({url: "http://m.soufun.com/clientindex.jsp?company=1076",log: i.log});
                    l[c[3]]()
                };
                o[c[4]](), o[c[5]]();
                if (typeof g.seajs === "object") {
                    g.seajs.use(m, p)
                } else {
                    n = f[c[9]]("script"), k = f.getElementsByTagName("head")[0], n.async = true, n.src = m, n.onload = p, k[c[10] + "Child"](n)
                }
            })
        }
    },log: function(i) {
        e.get("/public/?c=public&a=ajaxOpenAppData", {type: i,rfurl: f.referrer})
    },foo: function() {
        this.hide();
        try {
            d(c[0] + "_index", 1, 2), d(c[0], 1, 2), this.calClose()
        } catch (i) {
        }
    }};
    new a()
}(self, self.lib || (self.lib = {}));