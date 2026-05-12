# 🏆 Kabaddi Arena

Kabaddi Arena is an Android-based sports analytics application developed to track, analyze, and visualize kabaddi match performance in real time. The application helps players and coaches monitor match statistics, view performance insights, and share analytics visually.

---

# 📱 Features

## ✅ Live Match Tracking
- Track successful raids
- Track failed raids
- Track tackles
- Real-time point calculation
- Undo last action feature

---

## ⏱ Raid Timer
- Interactive circular 30-second raid timer
- One-tap countdown start
- Designed with sports-themed UI

---

## 📊 Performance Analytics
- Pie chart visualization
- Line chart performance comparison
- Success percentage calculation
- Dynamic match statistics

---

## 🗺 Heatmap Visualization
- Kabaddi court representation
- Successful raid zones
- Failed raid zones
- Dynamic marker positioning

---

## 📋 Match History
- Stores previous matches locally
- Card-based history UI
- Displays:
  - Opponent
  - Date
  - Raids
  - Successful raids
  - Failed raids
  - Tackles

---

## 📤 Share Performance
- Share performance analytics as an image
- Social media compatible
- Exports complete analytics screen

---

# 🛠 Technologies Used

| Technology | Purpose |
|---|---|
| Kotlin | Backend & business logic |
| XML | Android UI design |
| Room Database | Local database |
| SQLite | Data storage |
| MPAndroidChart | Analytics charts |
| RecyclerView | Match history display |
| FileProvider | Image sharing |
| CountDownTimer | Raid timer |

---

# 🧱 Project Architecture

```text
Frontend:
XML Layouts

Backend:
Kotlin Logic

Database:
Room Database + SQLite

Visualization:
MPAndroidChart
```

## 📂 Project Structure

```text
KabaddiArena
│
├── java/com.example.kabaddiarena
│   ├── MainActivity.kt
│   ├── MatchActivity.kt
│   ├── HistoryActivity.kt
│   ├── PerformanceActivity.kt
│   ├── HeatmapActivity.kt
│   ├── HistoryAdapter.kt
│   ├── MatchEntity.kt
│   ├── MatchDao.kt
│   └── AppDatabase.kt
│
├── res
│   ├── layout
│   │   ├── activity_main.xml
│   │   ├── activity_match.xml
│   │   ├── activity_history.xml
│   │   ├── activity_performance.xml
│   │   ├── activity_heatmap.xml
│   │   └── match_item.xml
│   │
│   ├── drawable
│   │   ├── timer_circle_bg.xml
│   │   └── kabaddi_logo.png
│   │
│   └── xml
│       └── file_paths.xml
│
├── manifests
│   └── AndroidManifest.xml
│
└── Gradle Scripts
    ├── build.gradle.kts
    └── settings.gradle.kts
```

# 📈 Results

The Kabaddi Arena application successfully achieved the following outcomes:

- ✅ Real-time kabaddi match tracking
- ✅ Dynamic raid timer implementation
- ✅ Match performance analytics using Pie and Line charts
- ✅ Local match history storage using Room Database
- ✅ Heatmap visualization for raid analysis
- ✅ Shareable performance card as image
- ✅ Modern sports-themed Android UI

The project demonstrates the practical use of Android development concepts including:
- Room Database
- RecyclerView
- MPAndroidChart
- FileProvider
- Bitmap image sharing
- Real-time UI updates
```
