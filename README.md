#rest-api-testing-training
The projects is created for the demonstration purposes during REST API testing training.
It consists of two modules:
- auto-test-framework
- rest-assured-overview

##auto-test-framework
Demonstrates an example of the test automation framework based on the following tools:
- REST Assured
- TestNG
- AssertJ
- Allure

Use maven command to execute the tests and generate a test result report:
mvn -pl auto-test-framework
clean test
-DpetstoreBaseUri=http://petstore.swagger.io 
-DpetstoreApiKey=1234567890 
allure:report

##rest-assured-overview
Demonstrates basic REST Assured tool usage scenarios.

#########################################################################

По домашке (на 12 окт ПТ):
чтобы закрепить рассмотренный материал по REST API тест дизайну и работе с REST Assured напишите по отдельному тесту на каждый метод (всего 7) для работы с Pet ресурсом

https://petstore.swagger.io/#/pet

Понимаю, что это задание частично дублирует предыдущее и повторяет то, что я сам показывал, но обязательно напишите эти тесты сами, продумывая то, как вы будете описывать запросы и какие проверки реализуете, подсматривайте в существующие примеры, но не делайте копи паст, постарайтесь разобраться и задавайте вопросы. Обязательно выполните задание, чтобы закрепить материал и быть готовыми к разбору следующих тем.

#######################################
# 1. create pet and verify body       #
# 2. get created pet and verify body  # 
# 3. update pet and verify body       #
# 4. get created pet and verify body  #
# 5. delete pet                       #
# 6. get pet and expect 404           #
#######################################