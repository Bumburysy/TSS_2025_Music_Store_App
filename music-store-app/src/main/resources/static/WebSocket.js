let stompClient = null;
let chart = null;

function connect() {
    const socket = new SockJS('/MusicStoreApplication/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("Połączono z WebSocket");

        stompClient.subscribe('/topic/inventory', function (message) {
            const albums = JSON.parse(message.body);
            console.log("Odebrano dane WebSocket:", albums);
            updateChart(albums);
        });

        fetch('/MusicStoreApplication/api/inventory')
            .then(response => response.json())
            .then(data => updateChart(data))
            .catch(err => console.error('Błąd ładowania danych:', err));
    });
}

function updateChart(albums) {
    const labels = albums.map(a => a.title);
    const data = albums.map(a => a.quantity);

    if (!chart) {
        chart = new Chart(document.getElementById('inventoryChart'), {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Stan magazynu',
                    data: data,
                    backgroundColor: '#00e676',
                    borderColor: '#00ff88',
                    borderWidth: 1,
                    hoverBackgroundColor: '#00ffaa',
                    hoverBorderColor: '#00ffaa'
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    } else {
        chart.data.labels = labels;
        chart.data.datasets[0].data = data;
        chart.update();
    }
}

function refreshInventory() {
    fetch('/MusicStoreApplication/api/inventory/refresh', {
        method: 'POST'
    })
        .then(resp => resp.text())
        .then(msg => console.log("Odświeżono:", msg))
        .catch(err => console.error("Błąd odświeżenia:", err));
}

connect();
