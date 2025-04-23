<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Movie Recommender Bot</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: #111;
      color: #eee;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      padding: 2rem;
    }
    .container {
      background: #1a1a1a;
      padding: 2rem;
      border-radius: 1rem;
      width: 100%;
      max-width: 500px;
    }
    h1 {
      text-align: center;
    }
    button {
      margin-top: 1rem;
      background: #007bff;
      color: white;
      border: none;
      padding: 0.75rem 1.5rem;
      border-radius: 0.5rem;
      cursor: pointer;
    }
    button:hover {
      background: #0056b3;
    }
    .question {
      margin: 1rem 0;
    }
    .result {
      margin-top: 2rem;
      font-size: 1.2rem;
      text-align: center;
    }
    input {
      width: 100%;
      padding: 0.5rem;
      margin-top: 0.5rem;
      margin-bottom: 1rem;
      border-radius: 0.5rem;
      border: none;
    }
  </style>
</head>
<body>
  <div class="container">
    <h1>🎬 Movie Recommender Bot</h1>
    <div id="app">
      <div id="agePrompt">
        <label for="age">Enter your age:</label>
        <input type="number" id="age" />
        <button onclick="startQuiz()">Start Quiz</button>
      </div>
      <div id="quiz" style="display:none;">
        <p class="question" id="questionText"></p>
        <button onclick="answer('yes')">Yes</button>
        <button onclick="answer('no')">No</button>
      </div>
      <div class="result" id="result"></div>
    </div>
  </div>

  <script>
    const questions = [
      { text: "Do you like action scenes?", category: "action" },
      { text: "Do you enjoy slapstick or silly humor?", category: "comedy" },
      { text: "Do you enjoy being scared?", category: "horror" },
      { text: "Do emotional stories stick with you?", category: "drama" },
      { text: "Are you fascinated by space or future tech?", category: "scifi" },
      { text: "Do you like superhero movies?", category: "action" },
      { text: "Do you enjoy stand-up comedy?", category: "comedy" },
      { text: "Do you watch horror movies alone?", category: "horror" },
      { text: "Do you cry during sad movies?", category: "drama" },
      { text: "Do you like time travel stories?", category: "scifi" },
      // Repeat to make 30
    ];
    while (questions.length < 30) {
      questions.push(...questions.slice(0, 5));
    }

    let age = 0;
    let currentQuestion = 0;
    const scores = { action: 0, comedy: 0, horror: 0, drama: 0, scifi: 0 };

    function startQuiz() {
      age = parseInt(document.getElementById("age").value);
      if (isNaN(age) || age < 1) {
        alert("Please enter a valid age.");
        return;
      }
      document.getElementById("agePrompt").style.display = "none";
      document.getElementById("quiz").style.display = "block";
      showQuestion();
    }

    function showQuestion() {
      document.getElementById("questionText").innerText =
        `(${currentQuestion + 1}/30) ` + questions[currentQuestion].text;
    }

    function answer(choice) {
      if (choice === 'yes') {
        const cat = questions[currentQuestion].category;
        scores[cat]++;
      }
      currentQuestion++;
      if (currentQuestion >= 30) {
        showResult();
      } else {
        showQuestion();
      }
    }

    function showResult() {
      document.getElementById("quiz").style.display = "none";
      const topGenre = Object.keys(scores).reduce((a, b) => scores[a] > scores[b] ? a : b);

      const recommendations = {
        action: age < 18 ? "Spider-Man: Into the Spider-Verse (PG)" : "John Wick (Rated R)",
        comedy: age < 18 ? "Paddington 2 (PG)" : "Superbad (Rated R)",
        horror: age < 18 ? "Scary Stories to Tell in the Dark (PG-13)" : "The Conjuring (Rated R)",
        drama: age < 18 ? "Wonder (PG)" : "The Shawshank Redemption (Rated R)",
        scifi: age < 18 ? "Big Hero 6 (PG)" : "Blade Runner 2049 (Rated R)"
      };

      document.getElementById("result").innerHTML =
        `🎉 You seem to love <strong>${topGenre.toUpperCase()}</strong> movies!<br><br>
         We recommend: <strong>${recommendations[topGenre]}</strong>`;
    }
  </script>
</body>
</html>
