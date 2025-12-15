document.getElementById("showWeatherBtn").addEventListener("click", showWeatherBars);

function getIcon(condition) {
  if (condition === "Sunny") return "☀";
  if (condition === "Rainy") return "🌧";
  if (condition === "Humidity") return "💧";
  return "";
}

async function showWeatherBars() {
  const city = document.getElementById("city").value.trim();
  const date = document.getElementById("date").value.trim();
  const resultDiv = document.getElementById("result-cards");
  const warningDiv = document.getElementById("warning-message");
  resultDiv.innerHTML = "";
  warningDiv.textContent = "";

  if (!city || !date) {
    warningDiv.textContent = "Please enter both city and date!";
    return;
  }

  const response = await fetch("dataset.csv");
  const data = await response.text();
  const rows = data.split("\n").slice(1);
  let found = false;

  for (let row of rows) {
    const [c, d, temp, hum, rain] = row.split(",");
    if (c && c.toLowerCase() === city.toLowerCase() && d === date) {
      found = true;
      const tempVal = Number(temp);
      const humVal = Number(hum);
      const rainVal = Number(rain);

      // Find max, but only highlight if threshold crossed
      let valueArray = [
        {type: "Sunny", value: tempVal,  threshold: 30},
        {type: "Humidity", value: humVal, threshold: 65},
        {type: "Rainy", value: rainVal, threshold: 2}
      ];
      // Only those above their respective threshold can glow
      let maxVal = Math.max(...valueArray.map(val => val.value));
      let glows = valueArray.map(v => (v.value === maxVal && v.value > v.threshold));

      resultDiv.appendChild(createBar("Sunny", temp + "°C",  "sunny", glows[0]));
      resultDiv.appendChild(createBar("Humidity", hum + "%", "humidity", glows[1]));
      resultDiv.appendChild(createBar("Rainy", rain + " mm", "rainy", glows[2]));
      break;
    }
  }
  if (!found) warningDiv.textContent = "Enter valid city and date from the dataset!";
}

function createBar(label, value, cssClass, shouldGlow) {
  const div = document.createElement("div");
  div.className = "weather-bar " + cssClass + (shouldGlow ? " glow" : "");
  div.innerHTML = `
    <span class="icon">${getIcon(label)}</span>
    <span class="bar-title">${label}</span>
    <span class="bar-value">${value}</span>
  `;
  return div;
}