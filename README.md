# AIMS License Generator

## Build

```
docker build -t harbor.solumesl.com/utils/aims-license-generator:0.0.1-SNAPSHOT .
```
## Run

```
docker run -p 8002:8080 harbor.solumesl.com/utils/aims-license-generator:0.0.1-SNAPSHOT
```

## Generate License

```
http://v0216.eu.solumesl.com:8002/license?licenseId=cede1ec4-e48b-3639-8306-373a2c78ec52&stores=10&years=1
```

Parameters:

* licenseId
* stores
* days
* months
* years

