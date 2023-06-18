const counters = document.querySelectorAll('[data-counter]');

if (counters) {
    counters.forEach(counter => {
        counter.addEventListener('click', e => {
            const target = e.target;

            if (target.closest('.counter__button')){
                let value = parseInt(target.closest('.counter').querySelector('input').value);/* для полуенчия числа */

                if (target.classList.contains('counter__button_plus')){
                    value++;
                } else {
                    --value;
                }

                if (value <= 1){
                    value = 1;
                }

                target.closest('.counter').querySelector('input').value = value.toString();
            }
        })
    })
}