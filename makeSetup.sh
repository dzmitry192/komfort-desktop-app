jpackage --input out/artifacts/project_for_university_jar \
  --dest dist \
  --main-jar project_for_university.jar \
  --main-class com.example.project_for_university.Main \
  --java-options '--enable-preview' \
  --name Komfort \
  --app-version 1.0 \
  --icon komfort.icns \
  --type dmg \

jpackage --input out/artifacts/project_for_university_jar \
  --dest dist \
  --main-jar project_for_university.jar \
  --main-class com.example.project_for_university.Main \
  --java-options '--enableт-preview' \
  --type msi \
  --win-shortcut \
  --win-menu \
  --name Komfort \
  --app-version 1.0 \
  --icon komfort.icns \

