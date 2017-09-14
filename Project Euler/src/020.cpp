#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

int main() {
	string liczba = "3628800", liczba1 = liczba, liczba2 = liczba;		/** zaczniemy od 10!; potrzebne liczba1 = liczba2 = liczba, aby ustalić
																			długość tychże liczb (nie możemy wpisać np. liczba1[6] przy zdefiniowaniu: liczba1 lub liczba1 = "" **/
	int temp, temp1, temp2, temp3, przeniesienie1, przeniesienie2, przeniesienie3, cyfra1, cyfra2, outcome = 0;
	
	for(int i = 11; i < 100; i++) {
		cyfra1 = i%10; cyfra2 = i/10;

		przeniesienie1 = 0;												/** liczba1 = liczba * cyfra1 **/
		przeniesienie2 = 0;												/** liczba2 = liczba * cyfra2 **/
        for(int j = liczba.length() - 1; j >= 0 ; j--) {
			temp = (int)(liczba[j]) - 48;
			temp1 = temp * cyfra1 + przeniesienie1;
			temp2 = temp * cyfra2 + przeniesienie2;
			liczba1[j] = (char)(temp1%10 + 48);
			liczba2[j] = (char)(temp2%10 + 48);
			przeniesienie1 = temp1/10;
			przeniesienie2 = temp2/10;
		}
		if(przeniesienie1 != 0)
			liczba1 = (char)(przeniesienie1 + 48) + liczba1;
		if(przeniesienie2 != 0)
			liczba2 = (char)(przeniesienie2 + 48) + liczba2;

		liczba2 = liczba2 + "0";										/** wyrównanie długości liczb **/
		while(liczba1.length() < liczba2.length())
			liczba1 = "0" + liczba1;
		while(liczba2.length() < liczba1.length())
			liczba2 = "0" + liczba1;
		while(liczba.length() < liczba1.length())						/** zmienna liczba ma mieć tą samą długość, co liczba1 i liczba2, abyśmy **/
			liczba = "0" + liczba;										/**	nie "wyszli poza tablicę" **/
		
        przeniesienie3 = 0;												/** liczba = liczba1 + liczba2 **/
		for(int j = liczba1.length() - 1; j >= 0; j--) {
			temp3 = (int)(liczba1[j]) + (int)(liczba2[j]) - 96 + przeniesienie3;
			liczba[j] = (char)(temp3%10 + 48);
			przeniesienie3 = temp3/10;
		}
		if(przeniesienie3 != 0)
			liczba = (char)(przeniesienie3 + 48) + liczba;
	}
	
	for(int i = 0; i < liczba.length(); i++)
		outcome += (int)(liczba[i]) - 48;
	
	cout << outcome << endl;

	system("pause");
	return 0;
}
