package com.example.jetbank.data.domain.usecase

import com.example.jetbank.data.domain.repository.BankRepository
import javax.inject.Inject


//usecaseler, repository ve viewmodel arasında bir bağlantıyı kuran ve İşlevibne göre ayırdığımız classlar.
//Bu sebeple usecaseler repo ları kulllanırlar o yüzxden inject edicez .
class GetBankDataUseCase @Inject constructor(private val bankrepository: BankRepository) {
    fun invoke()=bankrepository.getBankDataRepository()
}

//invoke operatörü, bir sınıfta tanımlanabilen ve o sınıfın örneklerinin sanki fonksiyonmuş gibi çağrılmasına izin veren özel bir fonksiyondur. invoke fonksiyonu , sınıfın bir nesnesinde parantez ( ) sözdizimini kullandığınızda çağrılır . Bu, kodunuzu daha öz ve anlamlı hale getirebilir ve özellikle belirli senaryolarda faydalıdır.