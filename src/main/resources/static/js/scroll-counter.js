const time = 1000;
const step = 1;

function outNum(num, elem) {
    let l = document.getElementById(elem);
    let n = 0;
    let t = Math.round(time / (num / step));

    let interval = setInterval(() => {
        if (n >= num) {
            clearInterval(interval);
        } else {
            n += step;
            l.innerHTML = n;
        }
    }, t);
}

outNum(50, 'out-1')
outNum(7, 'out-2')
outNum(2, 'out-3')
outNum(20, 'out-4')

function startAnimationOnIntersection() {
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                const num = parseInt(entry.target.getAttribute('data-number'));
                const elemId = entry.target.getAttribute('id');
                outNum(num, elemId); // Запуск анимации чисел
                observer.unobserve(entry.target);
            }
        });
    });

    const elements = document.querySelectorAll('[data-number]');
    elements.forEach(element => {
        observer.observe(element);
    });
}

window.addEventListener('DOMContentLoaded', () => {
    startAnimationOnIntersection();
});