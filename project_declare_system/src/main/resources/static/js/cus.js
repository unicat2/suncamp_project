(function ($) {

    "use strict";

    const wind = $(window);
    const body = $('body');


    preloader();
    /***
       *
       * Loading Page
       *
       */
    function preloader() {
        var preloader = $('.preloader');
        var preloader_block = preloader.find('.preloader-block');
        var progress_number = preloader_block.find('.percent');
        var progress_title = preloader_block.find('.title');
        var progress_loading = preloader_block.find('.loading');

        var preloader_bar = preloader.find('.preloader-bar');
        var preloader_progress = preloader_bar.find('.preloader-progress');

        var preloader_after = preloader.find('.preloader-after');
        var preloader_before = preloader.find('.preloader-before');


        var timer = dsnGrid.pageLoad(0, 100, 300, function (val) {
            progress_number.text(val);
            preloader_progress.css('width', val + '%')
        });


        wind.on('load', function () {

            clearInterval(timer);

            TweenMax.fromTo(preloader_progress, .5, { width: '95%' }, {
                width: '100%',
                onUpdate: function () {
                    var f = preloader_progress.width() / preloader_progress.parent().width() * 100;
                    progress_number.text(parseInt(f, 10));

                },
                onComplete: function () {
                    TweenMax.to(preloader_bar, .5, { left: '100%' });
                    TweenMax.to(progress_title, 1, { autoAlpha: 0, y: -100 });
                    TweenMax.to(progress_loading, 1, { autoAlpha: 0, y: 100 });
                    TweenMax.to(progress_number, 1, { autoAlpha: 0 });

                    TweenMax.to(preloader_before, 1, { y: '-100%', delay: .7 });
                    TweenMax.to(preloader_after, 1, {
                        y: '100%', delay: .7, onComplete: function () {
                            preloader.addClass('hidden');
                        }
                    });
                }
            });
        });


    }

    //    时间轴 bx
    function isElementInViewport(el) {
        var rect = el.getBoundingClientRect();
        return (
            rect.top >= 0 &&
            rect.left >= 0 &&
            rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            rect.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    }
    var items = document.querySelectorAll(".road-map-timeline li");

    function callbackFunc() {
        for (var i = 0; i < items.length; i++) {
            if (isElementInViewport(items[i])) {
                items[i].classList.add("in-view");
            }
        }
    }
    window.addEventListener("load", callbackFunc);
    window.addEventListener("scroll", callbackFunc);




    // 封面文字动效：
    (function ($) {
        $.fn.textyleF = function (options) {
            var target = this;
            var targetTxt = target.contents();
            var defaults = {
                duration: 1000,
                delay: 150,
                easing: 'ease',
                callback: null
            };
            var setting = $.extend(defaults, options);
            //split txt & wrap txt by span
            targetTxt.each(function () {
                var texts = $(this);
                if (this.nodeType === 3) {
                    mkspn(texts);
                }
            });
            function mkspn(texts) {
                texts.replaceWith(texts.text().replace(/(\S)/g, '<span>$1</span>'));
            }
            //txt animation
            return this.each(function (i) {
                var child = target.children('span');
                target.css('opacity', 1);
                child.css('display', 'inline-block');
                child.each(function (i) {
                    $(this).delay(setting.delay * i)
                        .queue(function (next) {
                            $(this).css({
                                transform: 'rotateY(0deg) rotateX(0deg)',
                                opacity: 1,
                                transitionDuration: setting.duration + 'ms',
                                transitionTimingFunction: setting.easing
                            })
                            next();
                        });
                    if (typeof setting.callback === 'function') {
                        $(this).on('transitionend', function () {
                            setting.callback.call(this);
                        });
                    }
                });
            });
        };
    }(jQuery));






})(jQuery);

