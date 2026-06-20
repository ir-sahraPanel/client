package ir.sahrapanel.app.core.domain.model

interface LocationRepository {
  suspend  fun getProvinces(): Result<List<Province>>
  suspend  fun getCitiesForProvince(provinceId: Long): Result<List<City>>
}