// Загрузка сохраненных значений при загрузке страницы
window.addEventListener('load', () => {
    const quantityInputs = document.getElementsByClassName('subtotal-quantity');
    Array.from(quantityInputs).forEach((input, index) => {
        const storedQuantity = localStorage.getItem(`quantity_${index}`);
        if (storedQuantity) {
            input.value = storedQuantity;
            updateSubtotal(index);
        }
    });
    updateTotal();
});

// Обработчик изменения значения quantity
const quantityInputs = document.getElementsByClassName('subtotal-quantity');
const subtotalValues = document.getElementsByClassName('subtotal-value');
const totalElement = document.getElementsByClassName('total-item')[0];

Array.from(quantityInputs).forEach((input, index) => {
    input.addEventListener('change', () => {
        const quantity = parseInt(input.value);
        const price = parseFloat(input.closest('tr').querySelector('.subtotal-price').textContent);
        const updatedSubtotal = quantity * price;
        subtotalValues[index].textContent = updatedSubtotal.toFixed(2);
        updateTotal();

        // Сохранение значения quantity и subtotal в локальное хранилище
        localStorage.setItem(`quantity_${index}`, quantity);
        localStorage.setItem(`subtotal_${index}`, updatedSubtotal.toFixed(2));
    });
});

// Функция обновления общей суммы
function updateTotal() {
    let total = 0;
    Array.from(subtotalValues).forEach((subtotal) => {
        total += parseFloat(subtotal.textContent);
    });
    totalElement.textContent = total.toFixed(2);
}

// Функция обновления subtotal для указанного индекса
function updateSubtotal(index) {
    const quantity = parseInt(quantityInputs[index].value);
    const price = parseFloat(quantityInputs[index].closest('tr').querySelector('.subtotal-price').textContent);
    const subtotal = quantity * price;
    subtotalValues[index].textContent = subtotal.toFixed(2);
}