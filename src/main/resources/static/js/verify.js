(function (window) {
    const l = 42, // 婊戝潡杈归暱
        r = 10, // 婊戝潡鍗婂緞
        w = 350, // canvas瀹藉害
        h = 50, // canvas楂樺害
        PI = Math.PI
    const L = l + r * 2 // 婊戝潡瀹為檯杈归暱

    function getRandomNumberByRange(start, end) {
        return Math.round(Math.random() * (end - start) + start)
    }

    function createCanvas(width, height) {
        const canvas = createElement('canvas')
        canvas.width = width
        canvas.height = height
        return canvas
    }

    function createImg(onload) {
        const img = createElement('img')
        img.crossOrigin = "Anonymous"
        img.onload = onload
        img.onerror = () => {
            img.src = getRandomImg()
        }
        img.src = getRandomImg()
        return img
    }

    function createElement(tagName) {
        return document.createElement(tagName)
    }

    function addClass(tag, className) {
        tag.classList.add(className)
    }

    function removeClass(tag, className) {
        tag.classList.remove(className)
    }

    function getRandomImg() {
        return 'https://picsum.photos/300/150/?image=' + getRandomNumberByRange(0, 100)
    }

    function draw(ctx, operation, x, y) {
        ctx.beginPath()
        ctx.moveTo(x, y)
        ctx.lineTo(x + l / 2, y)
        ctx.arc(x + l / 2, y - r + 2, r, 0, 2 * PI)
        ctx.lineTo(x + l / 2, y)
        ctx.lineTo(x + l, y)
        ctx.lineTo(x + l, y + l / 2)
        ctx.arc(x + l + r - 2, y + l / 2, r, 0, 2 * PI)
        ctx.lineTo(x + l, y + l / 2)
        ctx.lineTo(x + l, y + l)
        ctx.lineTo(x, y + l)
        ctx.lineTo(x, y)
        ctx.fillStyle = '#fff'
        ctx[operation]()
        ctx.beginPath()
        ctx.arc(x, y + l / 2, r, 1.5 * PI, 0.5 * PI)
        ctx.globalCompositeOperation = "xor"
        ctx.fill()
    }

    function sum(x, y) {
        return x + y
    }

    function square(x) {
        return x * x
    }

    class jigsaw {
        constructor(el, success, fail) {
            this.el = el
            this.success = success
            this.fail = fail
        }

        init() {
            this.initDOM()
            this.initImg()
            this.draw()
            this.bindEvents()
        }

        initDOM() {
            const canvas = createCanvas(w, h) // 鐢诲竷
            const block = canvas.cloneNode(true) // 婊戝潡
            const sliderContainer = createElement('div')
            const refreshIcon = createElement('div')
            const sliderMask = createElement('div')
            const slider = createElement('div')
            const sliderIcon = createElement('span')
            const text = createElement('span')

            block.className = 'block'
            sliderContainer.className = 'sliderContainer'
            refreshIcon.className = 'refreshIcon'
            sliderMask.className = 'sliderMask'
            slider.className = 'slider'
            sliderIcon.className = 'sliderIcon'
            text.innerHTML = '滑动验证'
            text.className = 'sliderText'

            const el = this.el
            el.appendChild(canvas)
            el.appendChild(refreshIcon)
            el.appendChild(block)
            slider.appendChild(sliderIcon)
            sliderMask.appendChild(slider)
            sliderContainer.appendChild(sliderMask)
            sliderContainer.appendChild(text)
            el.appendChild(sliderContainer)

            Object.assign(this, {
                canvas,
                block,
                sliderContainer,
                refreshIcon,
                slider,
                sliderMask,
                sliderIcon,
                text,
                canvasCtx: canvas.getContext('2d'),
                blockCtx: block.getContext('2d')
            })
        }

        initImg() {
            const img = createImg(() => {
                this.canvasCtx.drawImage(img, 0, 0, w, h)
                this.blockCtx.drawImage(img, 0, 0, w, h)
                const y = this.y - r * 2 + 2
                const ImageData = this.blockCtx.getImageData(this.x, y, L, L)
                this.block.width = L
                this.blockCtx.putImageData(ImageData, 0, y)
            })
            this.img = img
        }

        draw() {
            // 闅忔満鍒涘缓婊戝潡鐨勪綅缃�
            this.x = getRandomNumberByRange(L + 10, w - (L + 10))
            this.y = getRandomNumberByRange(10 + r * 2, h - (L + 10))
            draw(this.canvasCtx, 'fill', this.x, this.y)
            draw(this.blockCtx, 'clip', this.x, this.y)
        }

        clean() {
            this.canvasCtx.clearRect(0, 0, w, h)
            this.blockCtx.clearRect(0, 0, w, h)
            this.block.width = w
        }

        bindEvents() {
            this.el.onselectstart = () => false
            this.refreshIcon.onclick = () => {
                this.reset()
            }

            let originX, originY, trail = [], isMouseDown = false
            this.slider.addEventListener('mousedown', function (e) {
                originX = e.x, originY = e.y
                isMouseDown = true
            })
            document.addEventListener('mousemove', (e) => {
                if (!isMouseDown) return false
                const moveX = e.x - originX
                const moveY = e.y - originY
                if (moveX < 0 || moveX + 38 >= w) return false
                this.slider.style.left = moveX + 'px'
                var blockLeft = (w - 40 - 20) / (w - 40) * moveX
                this.block.style.left = blockLeft + 'px'

                addClass(this.sliderContainer, 'sliderContainer_active')
                this.sliderMask.style.width = moveX + 'px'
                trail.push(moveY)
            })
            document.addEventListener('mouseup', (e) => {
                if (!isMouseDown) return false
                isMouseDown = false
                if (e.x == originX) return false
                removeClass(this.sliderContainer, 'sliderContainer_active')
                this.trail = trail
                const {spliced, TuringTest} = this.verify()
                if (spliced) {
                    if (TuringTest) {
                        addClass(this.sliderContainer, 'sliderContainer_success')
                        this.success && this.success()
                    } else {
                        addClass(this.sliderContainer, 'sliderContainer_fail')
                        this.text.innerHTML = '鍐嶈瘯涓€娆�'
                        this.reset()
                    }
                } else {
                    addClass(this.sliderContainer, 'sliderContainer_fail')
                    this.fail && this.fail()
                    setTimeout(() => {
                        this.reset()
                    }, 1000)
                }
            })
        }

        verify() {
            const arr = this.trail // 鎷栧姩鏃秠杞寸殑绉诲姩璺濈
            const average = arr.reduce(sum) / arr.length // 骞冲潎鍊�
            const deviations = arr.map(x => x - average) // 鍋忓樊鏁扮粍
            const stddev = Math.sqrt(deviations.map(square).reduce(sum) / arr.length) // 鏍囧噯宸�
            const left = parseInt(this.block.style.left)
            return {
                spliced: Math.abs(left - this.x) < 10,
                TuringTest: average !== stddev, // 鍙槸绠€鍗曠殑楠岃瘉鎷栧姩杞ㄨ抗锛岀浉绛夋椂涓€鑸负0锛岃〃绀哄彲鑳介潪浜轰负鎿嶄綔
            }
        }

        reset() {
            this.sliderContainer.className = 'sliderContainer'
            this.slider.style.left = 0
            this.block.style.left = 0
            this.sliderMask.style.width = 0
            this.clean()
            this.img.src = getRandomImg()
            this.draw()
        }

    }

    window.jigsaw = {
        init: function (element, success, fail) {
            new jigsaw(element, success, fail).init()
        }
    }
}(window))