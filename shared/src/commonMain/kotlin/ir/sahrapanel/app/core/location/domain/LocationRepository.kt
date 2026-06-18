package ir.sahrapanel.app.core.location.domain

interface LocationRepository {
  suspend  fun getProvinces(): Result<List<Province>>
  suspend  fun getCitiesForProvince(provinceId: Long): Result<List<City>>
}