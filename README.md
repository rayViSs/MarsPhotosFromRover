# MarsPhotosFromRover

## Описание

Mars Rover Photos App - это мобильное приложение, которое отображает список фотографий сделанных марсоходами на Марсе. Приложение использует открытое Nasa API для получения данных о фотографиях. Фотографии загружаются и отображаются с помощью библиотеки Glide. Приложение также поддерживает пагинацию, что позволяет загружать и отображать большой объем фотографий по мере прокрутки списка. Вся архитектура приложения разработана в соответствии с принципами Clean Architecture и MVVM.

## Стек технологий:

RecyclerView, REST API (nasaapi), Retrofit, Hilt, Glide, Paging library, Clean Architecture

## Скриншоты

<div align="center">
  <img src="https://github.com/rayViSs/MarsPhotosFromRover/blob/main/app/src/main/res/screenshots/Screenshot_20230630_172608.png" alt="mainscreen" width="400">
  <img src="https://github.com/rayViSs/MarsPhotosFromRover/blob/main/app/src/main/res/screenshots/Screenshot_20230630_172643.png" alt="screen_details" width="400">
</div>

## Возможности

- Загрузка и отображение фотографий сделанных марсоходами на Марсе
- Поддержка пагинации для прокрутки большого объема фотографий
- Отображение деталей фотографии при нажатии на нее

## Установка и использование

1. Склонируйте репозиторий:

   ```bash
   git clone https://github.com/your-username/mars-rover-photos.git
2. Откройте проект в вашей интегрированной среде разработки (например, Android Studio).

3. Запустите проект на эмуляторе или устройстве Android.
